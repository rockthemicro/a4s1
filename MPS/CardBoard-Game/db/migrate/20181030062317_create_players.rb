class CreatePlayers < ActiveRecord::Migration[5.2]
  def change
    create_table :players do |t|
      t.string :name
      t.string :photo_url
      t.integer :attack
      t.integer :defence
      t.integer :position
      t.string :description
      t.integer :country_id
      t.integer :league_id
      t.timestamps
    end

    add_foreign_key :players,
                    :countries,
                    column: :country_id
    add_index :players, :country_id

    add_foreign_key :players,
                    :leagues,
                    column: :league_id
    add_index :players, :league_id
  end
end
