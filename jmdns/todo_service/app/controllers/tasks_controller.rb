class TasksController < ApplicationController
  include JsonExceptions
  
  def create
    @task = Task.create!(params[:content])
    
    respond_to do |format|
      format.json  { render :json => {:content => @task, :status => :created, :location => @task} }
    end
  end

  def destroy
    @task = Task.find(params[:id])
    @task.destroy

    respond_to do |format|
      format.json  { head :ok }
    end
  end

  def edit
    @task = Task.find(params[:id])
    
    respond_to do |format|
      format.json  { render :json => {:content => @task} }
    end
  end
  
  def index
    @tasks = Task.all
    
    respond_to do |format|
      format.json  { render :json => {:content => @tasks} }
    end
  end
  
  def new
    @task = Task.new

    respond_to do |format|
      format.json  { render :json => {:content => @task} }
    end
  end

  def show
    @task = Task.find(params[:id])
    
    respond_to do |format|
      format.json  { render :json => {:content => @task} }
    end
  end

  def update
    @task = Task.find(params[:id])
    @task.update_attributes!(params[:content])
    
    respond_to do |format|
      format.json  { render :json => {:content => @task}, :head => :ok }
    end
  end
  
end
