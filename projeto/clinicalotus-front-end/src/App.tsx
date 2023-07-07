import React, {useState} from 'react'
import './App.css'
import MainRouter from "./route/MainRouter";
import {AuthContext, User} from "./store/store";

function App() {
  const [user, setUser] = useState<User>()
  return (
    <AuthContext.Provider value={{user, setUser}}>
      <MainRouter/>
    </AuthContext.Provider>
  )
}

export default App
