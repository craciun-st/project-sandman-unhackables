import chartImage from './chart_img.png';
import './chart.css';
import React from 'react';
import ChartistGraph from 'react-chartist';
import { useState, useEffect } from 'react';



  

  

function ChartistLineGraph() {
    
    var hardCodedData = {
        labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
        series: [
          [12, 9, 7, 8, 5],
          [2, 1, 3.5, 7, 3],
          [1, 3, 4, 5, 6]
        ]
      };

    
    
      var options = {
        fullWidth: true,
        chartPadding: {
            top: 40,
            right: 40
        },
        showArea: false
      };
    

    return (
      <div className="ChartistLineGraph chartLimiter">
        {/* <ChartistGraph data={hardCodedData} options={options} type='Line' /> */}
        <img src={chartImage}></img>
      </div>
    )
  }


export default ChartistLineGraph;
