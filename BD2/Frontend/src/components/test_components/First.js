import React, { Component } from 'react';

class First extends Component {
    constructor(props) {
        super(props)
        this.timerId = 0
        this.timeoutId = 0
        this.goToSecond = this.goToSecond.bind(this)
    }

    hi() {
        console.log('da')
    }

    goToSecond() {
        this.props.history.push("/second")
        this.timeoutId = 0
    }

    componentDidMount() {
        this.timerId = setInterval(this.hi, 1000)
        this.timeoutId = setTimeout(this.goToSecond, 5000)
    }

    componentWillUnmount() {
        clearInterval(this.timerId)
        clearTimeout(this.timeoutId)
    }

    render() {
        return (
            <div>
                <h1>
                    Ana are mere
                </h1>
                <button>Ok</button>
            </div>
        )
    }
}

export default First;