import {API_URL} from "@env"

import {NativeModules} from 'react-native';

import { getDataSet } from "./data-helper"
const {FullSuiteModule} = NativeModules;


const runFullSuite = async (dataSetSize, numberOfRuns) => {
    console.log("Fetching dataset...")

    const dataSet = JSON.stringify(getDataSet(dataSetSize))

    console.log(`Starting full suite... For detailed information, see Logcat in Android Studio`)

    await FullSuiteModule.run(dataSet, numberOfRuns, API_URL)

    console.log(`Full suite done!`)
}

export default runFullSuite