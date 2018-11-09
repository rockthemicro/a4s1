class Effect < ApplicationRecord
  enum action: {change_stats: 0, remove_player: 1, change_goal_attempts: 2, change_goal_chance: 3}
  validates :action, presence: true
  enum apply_to: { player: 0, team: 1, country: 2, league: 3 }
  validates :apply_to, presence: true

  validates :attack, numericality: { only_integer: true }
  validates :defence, numericality: { only_integer: true }
  validates :goal_attempts, numericality: { only_integer: true }
  validates :goal_chance, numericality: { only_integer: true }
end
