/* frontend for interracting with benchmarking module */

import React, { useState } from 'react';
import {
    Button,
  Text,
  TextInput,
  View
} from 'react-native';

import {Picker} from '@react-native-picker/picker';

import runFullSuite from './utils/full-suite';

function App(): JSX.Element {
  const [dataSetSize, setDataSetSize] = useState(100)
  const [numberOfRuns, setNumberOfRuns] = useState(10)
  const [algorithm, setAlgorithm] = useState("AES-CBC")

return (
      <View>
        <Text>Data set size:</Text>
        <TextInput
          editable
          maxLength={10}
          onChangeText={text => setDataSetSize(parseInt(text))}
          value={dataSetSize.toString()}
          style={{padding: 10}}
        />


        <Text>Number of runs:</Text>

        <TextInput
          editable
          maxLength={10}
          onChangeText={text => setNumberOfRuns(parseInt(text))}
          value={numberOfRuns.toString()}
          style={{padding: 10}}
        />

        <Picker
          selectedValue={algorithm}
          onValueChange={(itemValue, itemIndex) =>
            setAlgorithm(itemValue)
          }>
          <Picker.Item label="AES-CBC" value="AES-CBC" />
          <Picker.Item label="AES-CTR" value="AES-CTR" />
          <Picker.Item label="AES-GCM" value="AES-GCM" />
          <Picker.Item label="BF-CBC" value="BF-CBC" />
          <Picker.Item label="ECIES-SECP256K1" value="ECIES-SECP256K1" />
          <Picker.Item label="RSA-OAEP" value="RSA-OAEP" />
          <Picker.Item label="RSA-PSS" value="RSA-PSS" />
          <Picker.Item label="ECDSA-P521" value="ECDSA-P521" />
        </Picker>


        
        <Button title='Start' onPress={() => runFullSuite(algorithm, dataSetSize, numberOfRuns)}></Button>
      </View>
  );
}

export default App;
