event.log('ITI parking enterance');

var tagName = Sensor.cached.tag


    const query = `SELECT * FROM APPLICATION_8390 WHERE tag = '${tagName}'`;
    function searchIn_callback(err, result) {
      if (err) {
        return event.error(err);
      }
      
      event.log(JSON.stringify(result))
      if (result.length > 0) {
      event.log('Allowed Car') 
      const opt= {
  "mqttUrl": "mot.mqtt_broker", // this is the default
    // broker url which maps to embedded MQTT Broker with
    // MasterOfThings platform. Change the url incase you want to
    // use another MQTT Broker.
    "port": "1883", // use 8883 for MQTTs
    "topic": "Phone/Parking",
    "username": "Parking_OS_Shebeen",
    "password": "Parking_OS_Shebeen_Pass",
    "qos": "0", // this key represents the Quality Of Service, Valid values are 0,1 or 2
    "retain": "false", //this key represents the message is retained or not, Valid values are true or false.
    "message": "open gate for car with tag:"+tagName // message must be string
};
      MqttPublish(opt, mqttPublish_callback);




function mqttPublish_callback(err){
  if (err){
    return event.error(err);
  }
event.log('Published');
event.end();
}
}else{
  event.error('Not Allowed Car');
}}
    SearchIn(query, searchIn_callback);
    
    
