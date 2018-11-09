import React, {Component} from 'react';
import "./Player.css"
import Draggable from "react-draggable";
import ReactDOM from "react-dom";
import {MainPage} from "./MainPage";
// const API = 'https://www.easports.com/fifa/ultimate-team/api/fut/item';
const item = {
  league: {
    url: "https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/leagueLogos/dark/2118.png",
    name: "Icons"
  },
  nation: {
    url: "https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/flags/list/54.png",
    name: "Brazil"
  },
  person: {
    url: "https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/portraits/237067.png",
    name: "Pele"
  },
  stats: {
    attack: 10,
    defense: 4,
  }
};

class Player extends Component {

  constructor(props) {
    super(props);
    this.state = {
      results: [],
      isLoading: false,
      error: null,
    };
  }

  componentDidMount() {
    this.setState({isLoading: true});
    try {
      this.setState({results: [item], isLoading: false})
    }
    catch {
      this.setState({error: "Unexpected error", isLoading: false});
    }
  }

  render() {
    const {results, isLoading, error} = this.state;

    if (error) {
      return <p>{error.message}</p>;
    }

    if (isLoading) {
      return <p>Loading ...</p>;
    }

    return (
      <Draggable>
        <div className="PlayerComponent">
          {results.map(r => {
            return <div>
              <img src={r.person.url} alt="new" className="PlayerThumbnail"/>
              <div className={"PlayerName"}>{r.person.name}</div>
              <div className="CardFooter">
                <img src={r.nation.url} alt="new" className="NationThumbnail"/>
                <img src={r.league.url} alt="new" className="LeagueThumbnail"/>
                <div className={"PlayerStats"}>A:{r.stats.attack} D:{r.stats.defense}</div>
              </div>
            </div>
          })}
        </div>
      </Draggable>
    );
  }
}

export default Player;
