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
  simIndex?: string;
}

const App = () => {
  const [data, setData] = useState<SmsData[]>([]);

  useEffect(() => {
    DeviceEventEmitter.addListener('onSmsReceive', ({sender, body}) =>
      setData(state => [...state, {sender, body}]),
    );
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={data}
        renderItem={({item}) => (
          <View style={styles.textContainer}>
            <Text style={styles.textSender}>{item.sender}</Text>
            <Text>:</Text>
            <Text style={styles.textBody}>{item.body}</Text>
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
    alignItems: 'center',
    gap: 10,
  },
  textSender: {
    fontSize: 16,
  },
  textBody: {
    fontSize: 24,
  },
});

export default App;
