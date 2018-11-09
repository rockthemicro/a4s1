Rails.application.routes.draw do
  scope '/api' do
    get :player, to: 'players#index'
    get :cards, to: 'gameboard#generate_cards'
    get :choose_cards, to: 'gameboard#choose_cards'
    get :update_gameboard, to: 'gameboard#update_gameboard'
  end
end
