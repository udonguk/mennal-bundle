import React, {useContext, useState} from "react";
import {
  AppBar,
  Box,
  ButtonBase,
  Container, Icon,
  IconButton,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  styled,
  SvgIcon,
  SwipeableDrawer,
  Toolbar,
  Typography
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import {Link, Outlet} from "react-router-dom";
import {observer} from "mobx-react";
import {StoresContext} from "../../../../store/RootStore";
import test from "../../../../resources/icons/free-animated-icon-checklist.png"
import ArticleIcon from '@mui/icons-material/Article';


const Navbar = observer(() => {
    const store = useContext(StoresContext);
    const surveyCategoryStore = store.surveyCategoryStore;

    const [menuIsOn, setMenuIsOn] = useState(false)

    const Offset = styled('div')(({theme}) => theme.mixins.toolbar);

    return (
      <Box sx={{ flexGrow: 1 }}>
        <AppBar enableColorOnDark position="fixed">
          <Toolbar>
            <IconButton
              id='menuButton'
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              sx={{ mr: 2 }}
              onClick={() => {
                setMenuIsOn(!menuIsOn)
              }}
            >
              <MenuIcon />
            </IconButton>

            <ButtonBase component={Link}
                        to={`/`}
            >
              <Typography
                variant="h6"
                component="div"
                sx={{ flexGrow: 1 }}
              >
                맨날
              </Typography>
            </ButtonBase>

            <SwipeableDrawer
              anchor={'left'}
              open={menuIsOn}
              onClick={() => setMenuIsOn(false)}
              onClose={() => setMenuIsOn(false)}
              onOpen={() => setMenuIsOn(false)}
            >
              <Box
                sx={{width: 200}}
                role={'presentation'}
              >
                <List>
                  {surveyCategoryStore.surveyCategories.map((item) => {
                    return (
                      <ListItem
                        id={`/survey/${item.code}`}
                        key={item.id}
                        component={Link}
                        to={`/survey/${item.code}`}
                      >
                        <ListItemIcon>
                          {/*<Icon>*/}
                          {/*  <img src={test} alt={'aaa'} style={{maxWidth: '100%', maxHeight: '100%'}}/>*/}
                          {/*</Icon>*/}
                          <ArticleIcon />
                        </ListItemIcon>
                        <ListItemText disableTypography primary={<Typography color={"text.primary"}>{item.title}</Typography>}/>
                      </ListItem>
                    )
                  })}
                </List>
              </Box>
            </SwipeableDrawer>
          </Toolbar>
        </AppBar>
        <Offset />
        <Container>
          <Outlet />
        </Container>
      </Box>
    )
  }
)
export default Navbar;
