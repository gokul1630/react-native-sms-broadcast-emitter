import React, {useEffect, useState} from 'react';
import {
  View,
  Text,
  StyleSheet,
  DeviceEventEmitter,
  FlatList,
} from 'react-native';

interface SmsData {
  body: string;
  sender: string;
  simIndex: string;
}

const App = () => {
  const [data, setData] = useState<SmsData[]>([]);

  useEffect(() => {
    DeviceEventEmitter.addListener('onSmsReceive', ({sender, body, simIndex}) =>
      setData(state => [...state, {sender, body, simIndex}]),
    );
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={data}
        renderItem={({item}) => (
          <View style={styles.textContainer}>
            <Text>{item.simIndex}</Text>
            <Text>:</Text>
            <Text>{item.sender}</Text>
            <Text>:</Text>
            <Text>{item.body}</Text>
          </View>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#2c3e50',
  },
  textContainer: {
    flexDirection: 'row',
    gap: 10,
  },
});

export default App;
