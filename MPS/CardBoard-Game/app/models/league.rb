class League < ApplicationRecord
  validates :name,      presence: true
  validates :badge_url, presence: true
end
