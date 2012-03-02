module JsonExceptions
  
  def self.included(base)
    base.rescue_from ActionController::RoutingError, :with => :route_error
    base.rescue_from ActionController::MethodNotAllowed, :with => :invalid_method
    base.rescue_from ActionController::UnknownAction, :with => :invalid_method
    base.rescue_from ActiveRecord::RecordNotFound, :with => :record_not_found
    base.rescue_from ActiveRecord::RecordInvalid, :with => :render_invalid_record
    base.rescue_from ActiveRecord::RecordNotSaved, :with => :render_invalid_record
  end
  

  private
  
  def trnsfr_error(exception)
    RAILS_DEFAULT_LOGGER.info "\n\n=======#{exception.message.inspect}=========\n\n"
    flash[:error] = "We had an error: #{exception.message}"
    redirect_to_back_or_index
  end

  def invalid_method(exception)
    RAILS_DEFAULT_LOGGER.info "\n\n=======#{exception.inspect}=========\n\n"
    flash[:error] = "Unfortunately you can't access the information in that way: #{exception}"
    redirect_to_back_or_index
  end
  
  def route_error(exception)
    RAILS_DEFAULT_LOGGER.info "\n\n=======#{exception.inspect}=========\n\n"
    flash[:error] = "We couldn't find that page: #{exception}"
    redirect_to_back_or_index
  end
  
  def record_not_found(exception)
    RAILS_DEFAULT_LOGGER.info "\n\n=======#{exception.inspect}=========\n\n"
    
    respond_to do |format|
      format.js   { render :nothing => true, :status => 404 }
      format.json { render :nothing => true, :status => 404 }
    end
  end

  def redirect_to_back_or_index
    request.env["HTTP_REFERER"] ||= "/"
    redirect_to(:back)
  end
  
  def render_invalid_record(exception)
    RAILS_DEFAULT_LOGGER.info "\n\n=======#{exception.inspect}=========\n\n"
    record = exception.record
    return @rescue_action.call if @rescue_action
    
    respond_to do |format|
      format.js   { render :json => record.errors.map, :status => :unprocessable_entity, :content_type => 'application/json' }
      format.json { render :json => record.errors.full_messages, :status => :unprocessable_entity }
    end
  end

end
