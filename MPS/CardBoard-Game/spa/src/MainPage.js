import React from 'react';
import "./MainPage.css"
import Player from "./Player";

export class MainPage extends React.Component {
  render() {
    return (
      <div className="MainPage">
        <p>
          Welcome to main page
        </p>
        <Player/>
      </div>
    );
  }
}
