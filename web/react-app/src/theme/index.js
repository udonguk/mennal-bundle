import _ from 'lodash';
import {THEMES} from "../constants";
import {createTheme} from "@mui/material";

const themesOptions = [
  {
    name: THEMES.LIGHT,
    palette: {
      type: 'light',
      primary: {
        main: '#000000',
      },
      secondary: {
        main: '#671bf1',
      },
      info: {
        main: '#2196f3',
      },
      success: {
        main: '#4caf50',
      },
    },
  },
  {
    name: THEMES.DARK,
    palette: {
      type: 'dark',
      primary: {
        main: '#000000',
      },
      secondary: {
        main: '#671bf1',
      },
      info: {
        main: '#2196f3',
      },
      success: {
        main: '#4caf50',
      },
    },
  }
]

export const createThemeByConfig = (config = {}) => {
  let themeOptions = themesOptions.find((theme) => theme.name === config.theme);

  console.debug(themeOptions)
  if (!themeOptions) {
    console.warn(new Error(`The theme ${config.theme} is not valid`));
    [themeOptions] = themesOptions;
  }

  return createTheme(
    _.merge(
      {},
      themeOptions
      // ,{ direction: config.direction }
    )
  );
}