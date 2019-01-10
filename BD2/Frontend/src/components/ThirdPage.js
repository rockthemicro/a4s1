import React, { Component } from 'react';
import '../css/ThirdPage.css'
import axios from "axios";

class ThirdPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            buy: false,
            vector: ['http://wallpapere.org/wp-content/uploads/2011/07/imagini-caini-albi.jpg',
                'https://cateldecatifea.ro/wp-content/uploads/ce-nume-sa-pun-cainelui.jpg',
                'https://bucuriacasei.ro/wp-content/uploads/2018/01/vand-golden-retriever.jpg',
                'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrzELnMYShiea4A-V0jPE0Z4UN4IrXXqfFH1iE2KFQl0ESkiDG']

        }
    }

    processBuy = () => {
        this.setState({buy: true});
    }

    render() {
        return (
            <div>
                { !this.state.buy &&
                <div>
                    <div className='titlu3' > {this.props.curr_elem.nume} </div>
                    {this.state.vector.map((elem, k) => {return(<img key={k} alt="" width="200" height="200" src = {elem} />);})}
                    <button onClick={this.processBuy}>CUMPARA</button>
                </div>}


                { this.state.buy && <FormContainer curr_elem={this.props.curr_elem} />}

            </div>
        );
    }
}

class FormContainer extends Component {
    constructor(props) {
        super(props)

        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.state = {
            localitati: [],
            livrari: [],
            ales_judet: false,
            ales_curier: false
        }
    }


    handleSubmit(event) {
        if (this.state.nume === undefined
            || this.state.prenume === undefined
            || this.state.adresa === undefined
            || this.state.telefon === undefined
            || this.state.ales_judet === false
            || this.state.ales_curier === false) {

            alert('Ati lasat campuri necompletate!')
            return
        }

        let idx = 0
        for (let i = 0; i < this.state.localitati.length; i++) {
            if (this.state.localitati[i].nume === this.state.judet) {
                idx = i
                break
            }
        }

        let url = 'http://localhost:8080/tranzactie'
        axios.post(url, {
            "id_curier":this.state.curier,
            "id_localitate":idx,
            "id_produs":this.props.curr_elem.id,
            "client_nume":this.state.nume,
            "client_prenume":this.state.prenume,
            "client_telefon":this.state.telefon,
            "adresa_livrare":this.state.adresa
        }, {})
            .then(response => {
                alert('Comanda dumneavoastra are id-ul ' + response.data.toString())
            })
            .catch(() => {
                console.log('Nu s-a putut efectua comanda')
            })

        event.preventDefault()
    }


    handleChange(event) {
        this.setState({[event.target.name]: event.target.value})

        if (event.target.name === 'judet') {
            let url = 'http://localhost:8080/curieri'
            axios.get(url, {params: {localitate: event.target.value}})
                .then(response => {
                    this.setState({
                        livrari: response.data,
                        ales_judet: true
                    })
                })
                .catch(() => {
                    console.log('Obtinerea curierilor a esuat!')
                })
        }

        if (event.target.name === 'curier') {
            this.setState({
                ales_curier: true
            })
        }
    }

    componentDidMount() {
        let url = 'http://localhost:8080/localitati'
        axios.get(url, {headers: {}})
            .then(response => {

                this.setState({
                    judet: response.data[0].nume,
                    localitati: response.data
                })


            })
            .catch(() => {
                console.log('Obtinerea localitatilor a esuat!')
            })
    }

    render() {
        return (
            <form className="formclass">
                <label>
                    <label className="FormTextBox"> Nume </label>
                    <input type="text" name="nume" onChange={this.handleChange}/>
                    <br/>
                    <br/>
                    <br/>

                    <label className="FormTextBox"> Prenume </label>
                    <input type="text" name="prenume" onChange={this.handleChange}/>
                    <br/>
                    <br/>
                    <br/>

                    <label className="FormTextBox"> Telefon </label>
                    <input type="text" name="telefon" onChange={this.handleChange}/>
                    <br/>
                    <br/>
                    <br/>

                    <label className="FormTextBox"> Adresa </label>
                    <input type="text" name="adresa" onChange={this.handleChange}/>
                    <br/>
                    <br/>
                    <br/>

                    <label className="FormTextBox"> Judet </label>
                    <select onChange={this.handleChange} name="judet">
                        {
                            this.state.localitati.map((elem, k) => {
                                return (
                                    <option key={k} value={elem.nume}> {elem.nume} </option>
                                )
                            })
                        }
                    </select>
                    <br/>
                    <br/>
                    <br/>

                    <label className="FormTextBox"> Curieri </label>
                    <select onChange={this.handleChange} name="curier">
                        {
                            this.state.livrari.map((elem, k) => {
                                let nume = ""
                                if (elem.id_curier == 1) {
                                    nume = "DHL"
                                } else if (elem.id_curier == 2) {
                                    nume = "Fan"
                                } else if (elem.id_curier == 3) {
                                    nume = "UPC"
                                }
                                return (
                                    <option key={k} value={elem.id_curier}> {nume} </option>
                                )
                            })
                        }
                    </select>
                    <br/>
                    <br/>
                    <br/>

                    <input type="submit" value="Submit" onClick={this.handleSubmit}/>
                </label>
            </form>
        );
    }
}

export default ThirdPage;