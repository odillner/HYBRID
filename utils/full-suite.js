/* Module for interracting with native benchmarking code */

import {API_URL} from "@env"

import {NativeModules} from 'react-native';

import { getDataSet } from "./data-helper"
const {FullSuiteModule} = NativeModules;


const runFullSuite = async (algorithm, dataSetSize, numberOfRuns) => {
    console.log("Fetching dataset...")

    const dataSet = JSON.stringify(getDataSet(dataSetSize))

    console.log(`Starting suite... For detailed information, see Logcat in Android Studio`)

    await FullSuiteModule.run(algorithm, dataSet, numberOfRuns)
}

export default runFullSuite