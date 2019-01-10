import React, { Component } from 'react';

class Welcome extends Component {

    componentDidMount() {
    }

    render(){
        return (
            <article className="article_class">
                <h1>Cine suntem noi?</h1>
                <p>Suntem cel mai bine cotat site de cumparaturi</p>
                <h1>Ce gasesti la noi?</h1>
                <p>Tot ce ti-ai putea imagina. De la masini si aparate electronice, la articole de imbracaminte si multe altele</p>
                <h1>De ce sa ramai pe acest site?</h1>
                <p>Cautam cele mai mici preturi si cele mai rapide livrari doar pentru tine</p>
                <h1>ENJOY</h1>
            </article>
        );
    }
}

export default Welcome;