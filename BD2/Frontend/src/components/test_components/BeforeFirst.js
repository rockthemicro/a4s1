import React, { Component } from 'react';
import {Link} from "react-router-dom";

class BeforeFirst extends Component {

    render() {
        return (
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/first">Home</Link>
                        </li>
                    </ul>
                </nav>
            </div>
        )
    }
}

export default BeforeFirst;