class Player < ApplicationRecord
  belongs_to :country
  belongs_to :league
  validates :name, presence: true
  validates :attack, numericality: { only_integer: true }
  validates :defence, numericality: { only_integer: true }
  enum position: { attacker: 0, midfielder: 1, defender: 2, goalkeeper: 3 }
  validates :position, presence: true
end
