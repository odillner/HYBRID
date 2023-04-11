import React, { useState } from 'react';
import {
    Button,
  Text,
  TextInput,
  View,
} from 'react-native';

import runFullSuite from './utils/full-suite';

function App(): JSX.Element {
  const [dataSetSize, setDataSetSize] = useState(100)
  const [numberOfRuns, setNumberOfRuns] = useState(10)

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

        
        <Button title='Start' onPress={() => runFullSuite(dataSetSize, numberOfRuns)}></Button>
      </View>
  );
}

export default App;
