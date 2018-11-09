# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

File.open('players_list.csv').each do |line|
  stats = line.split(',')
  country = Country.find_or_create_by(name: stats[4], flag_url: stats[5])
  league = League.find_or_create_by(name: stats[2], badge_url: stats[3])
  Player.create(name: stats[0],
                photo_url: stats[1],
                attack: stats[7].to_i,
                defence: stats[8].to_i,
                position: stats[6].to_s,
                country: country,
                league: league)
end

File.open('effect_cards_list.csv').each do |line|
  stats = line.split(',')
  Effect.create(action: stats[0].to_i,
                apply_to: stats[1].to_i,
                attack: stats[2].to_i,
                defence: stats[3].to_i,
                goal_attempts: stats[4].to_i,
                goal_chance: stats[5].to_i)
end
