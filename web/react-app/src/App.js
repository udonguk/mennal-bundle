import * as React from 'react'
import {useMemo} from 'react'
import './App.css';
import DocsLayout from "./layouts/DocsLayout";
import {Route, Routes, useLocation} from "react-router-dom";
import Intro from "./views/intro";
import {Box, CssBaseline, ThemeProvider} from "@mui/material";
import Survey from "./views/servey";
import GlobalStoreProvider from "./store/GlobalStoreProvider";
import {createThemeByConfig} from "./theme";
import {THEMES} from "./constants";
import {Helmet} from "react-helmet";
import GoogleAnalytics from "./components/GoogleAnalytics";
import SurveyItems from "./views/servey/SurveyItems";
import Result from "./views/result";


function App() {
  let location = useLocation();

  useMemo(() => {
    console.debug('location', location)
  }, [location])

  return (
    <GlobalStoreProvider>
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

        {/* Show the modal when a `backgroundLocation` is set */}
        {location.state && location.state.backgroundLocation && (
          <Routes>
            <Route path="/" element={<Intro />} />
          </Routes>
        )}
      </ThemeProvider>
    </GlobalStoreProvider>
  )
}

export default App;
