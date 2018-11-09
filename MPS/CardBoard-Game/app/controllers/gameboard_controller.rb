class GameboardController < ApplicationController
  @@gameboard = {}
  @@counter = 1

  @@all_player_cards1 = nil
  @@all_effect_cards1 = nil

  @@all_player_cards2 = nil
  @@all_effect_cards2 = nil

  def generate_cards
      player_cards = InitiateGameService.generate_player_cards
      effect_cards = InitiateGameService.generate_effect_cards

      render(
        status: 200,
        json: {"player_cards": player_cards, "effect_cards": effect_cards}
      )
  end

  def choose_cards
    if @@counter == 3
      @@counter = 1
      render(
        status: 400,
        json: { error: 'Choose cards called too many times ' }
      )
      return
    end

    param1 = params[:players]
    len1 = params[:players].length

    param2 = params[:effects]
    len2 = params[:effects].length

    arr_players = Array.new((len1 + 1) / 2) { |i| param1[i * 2] }
    arr_effects = Array.new((len1 + 1) / 2) { |i| param2[i * 2] }

    render(
      status: 200,
      json: {"player_count": @@counter}
    )

    if @@counter == 1
      all_player_cards1 = arr_players
      all_effect_cards1 = arr_effects

      @@gameboard['rounds_left'] = 3
      @@gameboard['score1'] = 0

      @@gameboard['attackers1'] = []
      @@gameboard['middle1'] = []
      @@gameboard['defenders1'] = []
      @@gameboard['goalkeeper1'] = 0

      @@gameboard['players1'] = arr_players
      @@gameboard['effects1'] = arr_effects
    end

    if @@counter == 2
      all_player_cards2 = arr_players
      all_effect_cards2 = arr_effects

      @@gameboard['score2'] = 0

      @@gameboard['attackers2'] = []
      @@gameboard['middle2'] = []
      @@gameboard['defenders2'] = []
      @@gameboard['goalkeeper2'] = 0

      @@gameboard['players2'] = arr_players
      @@gameboard['effects2'] = arr_effects
    end

    @@counter += 1
  end

  def get_gameboard

  end


  def update_gameboard

  end


end
