import React, { Component } from 'react';
import '../css/SecondPage.css'
import axios from "axios";

class SecondPage extends Component {
    constructor (props) {
        super(props);

        this.state = {
            vector: []
        }
    }

    componentDidMount() {
        let url = 'http://localhost:8080/produse'

        axios.get(url, {params: {categorie: this.props.value}})
            .then(response => {

                this.setState({
                    vector: response.data
                })


            })
            .catch(() => {
                console.log('Obtinerea produselor a esuat!')
            })
    }

    componentWillReceiveProps(nextProps, nextContext) {
        let url = 'http://localhost:8080/produse'

        axios.get(url, {params: {categorie: nextProps.value}})
            .then(response => {

                this.setState({
                    vector: response.data
                })


            })
            .catch(() => {
                console.log('Obtinerea produselor a esuat!')
            })
    }

    render() {
        return (
            <div>
                {this.state.vector.map(
                    (elem, k) => {

                        return (
                            <div key={k} className='clearfix'>
                                <img alt = "" src={elem.url_poza_profil} onClick={this.props.changePage(elem)} height="200" width="200" className='image'/>
                                <div className='titlu'>{elem.nume} </div>
                                <div className='text'>{elem.description}</div>
                            </div>
                        );
                    }
                )}
            </div>
        );
    }
}

export default SecondPage;