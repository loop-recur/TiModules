class ProjectsController < ApplicationController
  include JsonExceptions
  
  def create
    @project = Project.create!(params[:content])
    
    respond_to do |format|
      format.json  { render :json => {:content => @project, :status => :created, :location => @project} }
    end
  end

  def destroy
    @project = Project.find(params[:id])
    @project.destroy

    respond_to do |format|
      format.json  { head :ok }
    end
  end

  def edit
    @project = Project.find(params[:id])
    
    respond_to do |format|
      format.json  { render :json => {:content => @project} }
    end
  end
  
  def index
    @projects = Project.all
    
    respond_to do |format|
      format.json  { render :json => {:content => @projects} }
    end
  end
  
  def new
    @project = Project.new

    respond_to do |format|
      format.json  { render :json => {:content => @project} }
    end
  end

  def show
    @project = Project.find(params[:id])
    
    respond_to do |format|
      format.json  { render :json => {:content => @project} }
    end
  end

  def update
    @project = Project.find(params[:id])
    @project.update_attributes!(params[:content])
    
    respond_to do |format|
      format.json  { render :json => {:content => @project}, :head => :ok }
    end
  end
  
end
