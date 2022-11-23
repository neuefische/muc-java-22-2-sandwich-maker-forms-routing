import React, {useEffect, useState} from 'react';
import './App.css';
import axios from "axios";
import {
    BrowserRouter as Router,
    Route,
    Link,
    Routes
} from "react-router-dom";
import {Sandwich} from "./model/Sandwich";
import ShopPage from "./pages/ShopPage";
import SandwichPage from "./pages/SandwichPage";

function App() {
    // Creates a state "sandwiches" and gives us a method to change/set it
    const [sandwiches, setSandwiches] = useState([]);

    // Load Todos from backend
    useEffect(() => {
        loadSandwiches()

    }, []) // empty dependency array = execute only when website was rendered the first time

    const loadSandwiches = () => {
        axios.get("/api/sandwich/")
            .then((response) => response.data)
            .then((sandwiches) => setSandwiches(sandwiches))
    }

    const addSandwich = (newSandwich: Sandwich) => {
        console.log(newSandwich)

        // We use .then here to reload the sandwiches only when the get is done
        axios.post("/api/sandwich", newSandwich)
            .then(loadSandwiches) // reload sandwiches from backend
            .catch((errorFromBackend) => {
                console.log("ALARM", errorFromBackend)
            })
    }

    const deleteSandwich = (id: string) => {

        axios.delete("/api/sandwich/" + id)
            .then(loadSandwiches) // reload sandwiches from backend
    }

    return (
        <div className="App">
            <header className="App-header">
                <Router>
                    <Routes>
                        <Route path={"/"} element={<ShopPage sandwiches={sandwiches} addSandwich={addSandwich}
                                                             deleteSandwich={deleteSandwich}/>}/>
                        <Route path={"/sandwiches/:id"} element={<SandwichPage sandwiches={sandwiches}/>}/>
                    </Routes>
                </Router>
            </header>
        </div>
    );
}

export default App;
