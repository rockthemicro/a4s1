class Country < ApplicationRecord
  validates :name,     presence: true
  validates :flag_url, presence: true
end
