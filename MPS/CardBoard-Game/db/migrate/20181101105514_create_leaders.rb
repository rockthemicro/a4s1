class CreateLeaders < ActiveRecord::Migration[5.2]
  def change
    create_table :leaders do |t|
      t.string :name
      t.string :photo_url
      t.string :description
      t.integer :country_id
      t.integer :league_id
      t.integer :effect_id
      t.timestamps
    end
    add_foreign_key :leaders,
                    :countries,
                    column: :country_id
    add_index :leaders, :country_id

    add_foreign_key :leaders,
                    :leagues,
                    column: :league_id
    add_index :leaders, :league_id

    add_foreign_key :leaders,
                    :effects,
                    column: :effect_id
    add_index :leaders, :effect_id
  end
end
