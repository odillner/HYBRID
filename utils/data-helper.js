/* Module used for handling test data */
import dataSet100 from "../data/100.json"
import dataSet1000 from "../data/1000.json"
import dataSet10000 from "../data/10000.json"

const getDataSet = (size) => {
    switch(size) {
        case 100:
          return dataSet100
        case 1000:
          return dataSet1000
        case 10000:
          return dataSet10000
        default:
          return dataSet10000
      } 
}

export {getDataSet}