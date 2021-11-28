import * as React from 'react'
import './App.css';
import DocsLayout from "./layouts/DocsLayout";
import {Route, Routes} from "react-router-dom";
import Intro from "./views/intro";
import {Box, CssBaseline, ThemeProvider} from "@mui/material";
import Survey from "./views/servey";
import GlobalStoreProvider from "./store/GlobalStoreProvider";
import {createThemeByConfig} from "./theme";
import {THEMES} from "./constants";

function App() {
  return (
    <GlobalStoreProvider>
      <ThemeProvider theme={createThemeByConfig({
        theme: THEMES.LIGHT
      })}>
        <CssBaseline />
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
      </ThemeProvider>
    </GlobalStoreProvider>
  )
}

export default App;
