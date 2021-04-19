import React, { Component } from 'react';
import { getHelloMessage } from "../actions/helloAction";

class Hello extends Component {
  constructor(props) {
    super(props);
    this.state = {
      message: 'No message from server',
      json_msg: []
    };
  }

  componentDidMount() {
    this._isMounted = true;
    getHelloMessage().then(message => {
      if (this._isMounted) {
        var json = [];
        var nonDuplicates = "";
        var duplicates = "";
        var converted;
        var recordArray = message.split(";");
        recordArray.pop();
        console.log("records", recordArray);
        try {
            recordArray.forEach( record => {
                converted = JSON.parse(record);
                json.push(converted);
                if(converted.duplicate === "true"){
                    if (duplicates !== ""){
                        duplicates += ", ";
                    }
                    duplicates += converted.id + ":" + converted.first_name;
                }
                else {
                    if (nonDuplicates !== ""){
                        nonDuplicates += ", ";
                    }
                    nonDuplicates += converted.id + ":" + converted.first_name;
                }
            } );
        } catch (error) {
            console.error(error);
        }
        console.log("json", json);
        message = "ORIGINAL RECORDS\n" + nonDuplicates + "\nDUPLICATE RECORDS\n" + duplicates;
        this.setState( {message, json} );
        }
    }).catch(() => {
      if (this._isMounted)
        this.setState( {message: 'Server not responding', json_msg: []} );
    });
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  render() {
    return (
      <div>{this.state.message}</div>
    );
  }
}

export default Hello;
