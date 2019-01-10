import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
/* pentru exemplul didactic
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import First from './test_components/First.js'
import BeforeFirst from './test_components/BeforeFirst.js'
import Second from './test_components/Second.js'
*/
import Welcome from './Welcome.js'
import SecondPage from './SecondPage.js'
import ThirdPage from './ThirdPage.js'

class App extends Component {
    constructor (props) {
        super(props)

        this.state = {
            vector: ['Laptopuri',  'Monitoare', 'Telefoane', 'Carti', 'Caiete'],
            page: 1
        };

        this.changeToThirdPage = this.changeToThirdPage.bind(this)
    };


    getButtonClickFunction(value) {
        return () => {
            this.setState({
                page: 2,
                value: value
            })
        }
    }

    componentDidMount() {

        let url = 'http://localhost:8080/categorii'
        axios.get(url, {headers: {}})
            .then(response => {

                this.setState({
                    vector: response.data
                })


            })
            .catch(() => {
                console.log('Obtinerea categoriilor a esuat!')
            })

        /*
        let url = 'http://localhost:8080/greeting'
        let testurl = 'http://localhost:8080/test'

        let getConfig = {
            headers: {

            },
            params: {
                name: 'Mihaela'
            }
        }
        axios.get(url, getConfig)
            .then(response => {
                console.log(response)
                this.setState({arrived:true})
            })
            .catch(() => console.log("Can’t access " + url + " response. Blocked by browser?"))

        let postData = {
            text: "ce faci",
        }

        let postConfig = {

        }

        axios.post(testurl, postData, postConfig)
            .then(response => {
                console.log(response)
            })
            .catch(() => {
                console.log("Can't access " + testurl)
            })
        */
    }

    changeToThirdPage(elem) {
        return () => {
            this.setState({
                page: 3,
                current_elem: elem
            })
        }
    }

    render() {
        return (

                <div>
                    <h1 className='title'> Bun venit la Soni Shop!</h1>
                    <section className="container">
                        <div className="left-half">
                            <article className="article_class">
                                <h1>Categorii de Produse</h1>
                                {this.state.vector.map((value, k) => {
                                    return (
                                        <p key={k}>
                                            <button className={"button_categorie"} onClick={this.getButtonClickFunction(value)}>{value}</button>
                                        </p>
                                    );
                                })}
                            </article>
                        </div>


                        <div className="right-half">
                            {this.state.page === 1 && <Welcome/>}
                            {this.state.page === 2 && <SecondPage changePage={this.changeToThirdPage} value={this.state.value}/>}
                            {this.state.page === 3 && <ThirdPage  curr_elem={this.state.current_elem}/>}
                        </div>
                    </section>
                </div>
        )
  }
}

export default App;

/* exemplu rutare

<Router>
    <Switch>
        <Route exact path="/" component={Welcome}/>

        <Route exact path="/zero" component={BeforeFirst}/>
        <Route exact path="/first" component={First}/>
        <Route exact path="/second" component={Second}/>
    </Switch>
</Router>

*/