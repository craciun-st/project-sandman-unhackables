import React, { Component } from 'react'
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import './TaskImportanceSelector.css'

export default class TaskImportanceSelector extends Component {
    
    render() {
        const updateImportance = this.props.updateImportance;
        

        return (
            <div className="btn-group" role="group" ariaLabel="Basic radio toggle button group">
                <input type="radio" className="btn-check" name="btnradio" id="btnradio1" autocomplete="off" data-value="1" onClick={updateImportance}/>
                <label className="btn btn-outline-primary task-importance" htmlFor="btnradio1">Insignificant</label>

                <input type="radio" className="btn-check task-importance" name="btnradio" id="btnradio2" autocomplete="off" data-value="2" onClick={updateImportance}/>
                <label className="btn btn-outline-primary task-importance" htmlFor="btnradio2">Basic</label>

                <input type="radio" className="btn-check task-importance" name="btnradio" id="btnradio3" autocomplete="off" data-value="3" onClick={updateImportance}/>
                <label el className="btn btn-outline-primary task-importance" htmlFor="btnradio3">Normal</label>            
                
                <input type="radio" className="btn-check task-importance" name="btnradio" id="btnradio4" autocomplete="off" data-value="4" onClick={updateImportance}/>
                <label el className="btn btn-outline-primary task-importance" htmlFor="btnradio4">Significant</label>            

                <input type="radio" className="btn-check task-importance" name="btnradio" id="btnradio5" autocomplete="off" data-value="5" onClick={updateImportance}/>
                <label className="btn btn-outline-primary task-importance" htmlFor="btnradio5">Must Do!</label>            
                
            </div>
        )
        
    }
    
}
