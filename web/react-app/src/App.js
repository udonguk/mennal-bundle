import * as React from 'react'
import './App.css';
import DocsLayout from "./layouts/DocsLayout";
import {Route, Routes} from "react-router-dom";
import Intro from "./views/intro";
import {Box} from "@mui/material";
import Survey from "./views/servey";

// import PropTypes from 'prop-types'


function App({props}) {
  return (
      <Routes>
        <Route path={"/"} element={<DocsLayout />}>
          <Route path={"/survey"}
                 element={<Survey />} />
          <Route
            index
            element={<Intro />}
          />
          <Route
            path={'*'}
            element={
              <Box>
                there's nothing here!
              </Box>
            }
          />
        </Route>
      </Routes>
  )
}

export default App;
