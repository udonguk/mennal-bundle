import * as React from 'react'
import './App.css';
import DocsLayout from "./layouts/DocsLayout";
import {Route, Routes} from "react-router-dom";
import Intro from "./views/intro";
import {Box, CssBaseline, ThemeProvider} from "@mui/material";
import Survey from "./views/servey";
import {createThemeByConfig} from "./theme";
import {THEMES} from "./constants";
import {Helmet} from "react-helmet";
import GoogleAnalytics from "./components/GoogleAnalytics";
import SurveyItems from "./views/servey/SurveyItems";
import Result from "./views/result";


function App() {
  return (
      <ThemeProvider theme={createThemeByConfig({
        theme: THEMES.LIGHT
      })}>
        <CssBaseline />
        <Helmet>
          <title>맨날</title>
        </Helmet>
        <GoogleAnalytics />
        <Routes>
          <Route path={"/"} element={<DocsLayout />}>
            <Route
              index
              element={<Intro />}
            />
            <Route path={"survey"} element={<Survey />} >
              <Route path={":surveyCategoryCode"} element={<SurveyItems />} />
            </Route>
            <Route path={"/result"} element={<Result />} />
            <Route
              path={'*'}
              element={
                <Box>
                  there's nothing here!
                </Box>
              }
            />
          </Route>
          <Route path={"/test"}
                 element={
                   <Box>
                     test
                   </Box>
                 }
          />
        </Routes>
      </ThemeProvider>
  )
}

export default App;
