class Project < ActiveRecord::Base
  has_many :tasks
  
  def to_json(value=nil)
    {:name => name, :tasks => self.tasks.map(&:to_json), :self => "/projects", :guid => "/projects/#{id}"}.to_json
  end
end
