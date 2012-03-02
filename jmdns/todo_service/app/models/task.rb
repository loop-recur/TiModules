class Task < ActiveRecord::Base
  attr_accessor :self, :guid
  belongs_to :project
  
  def to_json(value=nil)
    {:done => done?, :description => description, :order => order, :self => "/tasks", :guid => "/tasks/#{id}"}.to_json
  end
end
