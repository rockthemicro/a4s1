class Leader < ApplicationRecord
  belongs_to :country
  belongs_to :league
  belongs_to :effect
  validates :name, presence: true
end
