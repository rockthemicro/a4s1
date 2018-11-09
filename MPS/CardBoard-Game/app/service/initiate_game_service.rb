module InitiateGameService
  class << self
      def generate_player_cards
        players = Player.all.to_a

        ret = players.sample(24)
        return ret
      end

      def generate_effect_cards
        effects = Effect.all.to_a

        ret = effects.sample(10)
        return ret
      end
    end
end
