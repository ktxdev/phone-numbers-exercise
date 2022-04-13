import React from 'react'
import {AppBar, Container, Typography, Toolbar} from "@mui/material";

const Navbar = () => {
  return (
    <AppBar position="static">
        <Container maxWidth="xl">
          <Toolbar disableGutters>
            <Typography
                variant="h6"
                noWrap
                component="div"
                sx={{ mr: 2, display: { xs: 'none', md: 'flex' } }}
            >
              Phone Numbers
            </Typography>
          </Toolbar>
        </Container>
      </AppBar>
  )
}

export default Navbar