import React, {useContext, useState} from "react";
import {
  AppBar,
  Box,
  Container,
  IconButton,
  List,
  ListItem,
  ListItemText,
  styled,
  SwipeableDrawer,
  Toolbar,
  Typography
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import {Link, Outlet} from "react-router-dom";
import {observer} from "mobx-react";
import {StoresContext} from "../../../../store/RootStore";


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
            <Typography
              variant="h6"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              맨날
            </Typography>
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
                        button
                        key={item.id}
                        component={Link}
                        to={`/survey/${item.code}`}
                      >
                        <ListItemText primary={item.title} />
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