import _ from 'lodash';
import {THEMES} from "../constants";
import {createTheme} from "@mui/material";

const themesOptions = [
  {
    name: THEMES.LIGHT,
    palette: {
      type: 'light',
      primary: {
        main: '#3AB0FF',
        light: '#8cccfb',
        dark: '#0098ff',
      },
      secondary: {
        main: '#FFB562',
      },
      info: {
        main: '#2196f3',
      },
      success: {
        main: '#4caf50',
      },
      background: {
        default: '#F9F2ED',
      },
      error: {
        main: '#F87474',
      },
    },
    typography: {
      fontFamily: 'GangwonEdu_OTFBoldA',
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
    typography: {
      fontFamily: 'GangwonEdu_OTFBoldA',
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
