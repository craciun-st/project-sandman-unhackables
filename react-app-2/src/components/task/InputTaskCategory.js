import React, { Component } from 'react'

export default class InputTaskCategory extends Component {
    render() {
        return (
            <div>
                <input
                            list="default-categories"
                            id="category-choice"
                            name="category-choice"
                            className=""
                            value={this.props.taskCategory}
                            onChange={this.props.updateTaskCategory}
                            placeholder="Enter task category..."
                />
                        
                <datalist id="default-categories">
                    {this.props.defaultCategories.map(
                        (category, index) => (
                            <option value={category} key={index}/>
                        )
                    )}
                </datalist>
            </div>          
        )
    }
}
