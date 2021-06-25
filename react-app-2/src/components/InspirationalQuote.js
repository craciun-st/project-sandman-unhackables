import './InspirationalQuote.css'
import {Component} from "react";

export default class InspirationalQuote extends Component {
    render() {
        return (
            <div className="inspire-quote">
                <div className="quote-text">
                    {this.props.quote}
                </div>
                <div className="quote-author">
                    {this.props.author}
                </div>
            </div>
        )
    }
}