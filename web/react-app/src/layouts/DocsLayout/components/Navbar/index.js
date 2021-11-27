import React, {useState} from "react";
import {
  AppBar,
  Box, Container,
  CssBaseline,
  IconButton,
  List,
  ListItem, ListItemText, Slide,
  SwipeableDrawer,
  Toolbar,
  Typography, useScrollTrigger
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import {Outlet} from "react-router-dom";


// todo 리엑트 appbar 샘플 돌려볼것
const HideOnScroll = (props) => {
  const { children, window } = props;
  // Note that you normally won't need to set the window ref as useScrollTrigger
  // will default to window.
  // This is only being set here because the demo is in an iframe.
  const trigger = useScrollTrigger({
    target: window ? window() : undefined,
  });

  return (
    <Slide appear={false} direction="down" in={!trigger}>
      {children}
    </Slide>
  );
}

const Navbar = ({props}) => {
  const [menuIsOn, setMenuIsOn] = useState(false)

  return (
    <Box sx={{ flexGrow: 1 }}>
      <CssBaseline />
      <HideOnScroll {...props}>
        <AppBar enableColorOnDark position="static">
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
                  {['테스트1', '테스트2', '테스트3', '테스트4'].map((item) => {
                    return (
                      <ListItem button key={item}>
                        <ListItemText primary={item} />
                      </ListItem>
                    )
                  })}
                </List>
              </Box>
            </SwipeableDrawer>
          </Toolbar>
        </AppBar>
      </HideOnScroll>
      <Toolbar  />
      <Container>
        <Outlet />
        {/*<Box>*/}
        {/*  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus aspernatur cum deleniti ea esse, fuga fugit harum incidunt iusto laborum non, nostrum, totam ut voluptatibus voluptatum? Assumenda commodi consequatur cum cupiditate delectus distinctio harum incidunt, minima molestiae nam nisi, obcaecati pariatur quaerat quidem quo repudiandae sint sunt veniam vitae voluptate? Ab, amet, assumenda aut beatae blanditiis consectetur distinctio dolor eveniet facilis hic in inventore ipsam itaque maxime minima minus nihil odio provident quae quas qui, quo repudiandae sed vero vitae voluptate voluptatem voluptates. Ab accusamus accusantium consequatur error esse eveniet ex exercitationem harum libero maiores molestias nam necessitatibus nostrum numquam obcaecati porro quidem quos reprehenderit repudiandae sapiente sequi temporibus totam, ullam ut voluptates. Ab aut beatae explicabo nisi nobis possimus provident quam quasi ut! Ab atque blanditiis consectetur delectus dolores doloribus eligendi expedita harum ipsa ipsum laudantium minima nemo neque nesciunt nisi officia perspiciatis, placeat porro possimus provident qui quibusdam reiciendis repellat reprehenderit ullam veritatis voluptate voluptatum? Dicta molestias nemo nihil quasi! Autem et in iure laudantium nam natus qui rem. Accusantium adipisci, amet deserunt eligendi facere magnam minima nam non nulla, recusandae sapiente sed tempore vitae. Animi consectetur error nobis quam quia sunt unde vero. Ab cupiditate dolorum ex id maxime mollitia quia rem similique, tempora voluptate. Debitis distinctio eligendi hic illum praesentium quasi rerum, sed? Accusamus aliquid consectetur dignissimos, doloremque dolores excepturi facilis, fuga in iste itaque nam neque possimus quam quis recusandae, rem saepe sunt ullam veritatis vitae. Aliquam debitis dolor doloremque hic laboriosam odio quam rerum suscipit vero voluptates. Animi aperiam assumenda cupiditate dignissimos iure, laborum molestiae nam nihil nostrum perferendis praesentium quo recusandae tempore ut voluptas, voluptate voluptatum! Aliquid amet consequatur deleniti dicta ea et harum libero necessitatibus nemo, nostrum omnis porro quaerat rerum sapiente voluptas. Asperiores cumque delectus distinctio ea eius exercitationem ipsa necessitatibus, optio perferendis quod repudiandae rerum temporibus vero? Dolorum, eaque facilis. Aliquid amet aperiam, corporis debitis delectus doloribus ducimus enim id iste laborum maxime molestias nemo nobis, odit optio placeat quaerat quia quod quos ratione sed suscipit tenetur vitae? Accusantium autem enim maxime repellendus voluptas. Alias amet autem delectus esse, est labore mollitia quaerat quas quasi, quia quisquam saepe totam voluptatum. Aliquid dicta enim esse ipsam maiores modi odio quae quia velit voluptate? Ab accusantium aliquam aliquid consequatur doloremque eaque enim ex expedita facere id ipsa laudantium magnam magni modi natus nemo neque nostrum numquam odio officia placeat porro quaerat quas quasi quia quisquam quod reiciendis, repellat repudiandae sed sint sit soluta temporibus tenetur totam ullam, voluptates. Alias aliquam amet animi aut debitis ducimus eius ex nisi quia suscipit? Assumenda doloribus eveniet expedita facere facilis harum illo rem. Ab aliquid architecto beatae cupiditate exercitationem facere, fuga inventore ipsa laboriosam laborum officia pariatur quis rerum similique velit? Blanditiis distinctio dolor dolorum labore odit omnis. Maxime quis quo repellendus rerum. Aliquam animi consectetur consequuntur culpa, debitis distinctio eos minus modi nesciunt nihil obcaecati pariatur perspiciatis quia repellendus saepe, similique soluta ut vel veniam, voluptates! Cumque distinctio doloribus ex quod sed similique totam veritatis!*/}
        {/*</Box>*/}
      </Container>
    </Box>
  )
}

export default Navbar;