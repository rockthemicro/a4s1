class CreateEffects < ActiveRecord::Migration[5.2]
  def change
    create_table :effects do |t|
      t.integer :apply_to
      t.integer :country_id
      t.integer :league_id
      t.integer :action
      t.integer :apply_to
      t.integer :attack
      t.integer :defence
      t.integer :goal_chance
      t.integer :goal_attempts
      t.timestamps
    end

    add_foreign_key :effects,
                    :countries,
                    column: :country_id
    add_index :effects, :country_id

    add_foreign_key :effects,
                    :leagues,
                    column: :league_id
    add_index :effects, :league_id
  end
end
