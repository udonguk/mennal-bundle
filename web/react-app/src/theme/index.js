import _ from 'lodash';
import {THEMES} from "../constants";
import {colors, createTheme} from "@mui/material";

const themesOptions = [
  {
    name: THEMES.LIGHT,
    palette: {
      type: 'light',
      primary: {
        main: '#673ab7',
      },
      secondary: {
        main: '#9575cd',
      },
      info: {
        main: '#2196f3',
      },
      background: {
        default: colors.common.white,
        dark: '#f4f6f8',
        paper: colors.common.white
      },
      text: {
        primary: colors.blueGrey[900],
        secondary: colors.blueGrey[600]
      }
    },
  },
  {
    name: THEMES.DARK,
    palette: {
      type: 'dark',
      primary: {
        main: '#673ab7',
      },
      background: {
        default: '#2a2d3d',
        dark: '#222431',
        paper: '#2a2d3d'
      },
      secondary: {
        main: '#9575cd',
      },
      info: {
        main: '#2196f3',
      },
      text: {
        primary: '#f6f5f8',
        secondary: '#9699a4'
      }
    }
  }
]

export const createThemeByConfig = (config = {}) => {
  let themeOptions = themesOptions.find((theme) => theme.name === config.theme);

  console.debug(themeOptions)
  if (!themeOptions) {
    console.warn(new Error(`The theme ${config.theme} is not valid`));
    [themeOptions] = themesOptions;
  }

  let theme = createTheme(
    _.merge(
      {},
      themeOptions
      // ,{ direction: config.direction }
    )
  );

  // if (config.responsiveFontSizes) {
  //   theme = responsiveFontSizes(theme);
  // }

  return theme;
}