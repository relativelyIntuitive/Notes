import React from 'react';

import Axios from '../../../server/node_modules/axios';
import { navigate } from '@reach/router';

import 'bootstrap/dist/css/bootstrap.min.css';
import cd_icon from '../static/images/cd_icon.png';

import { Button } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import FormControl from 'react-bootstrap/FormControl';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

import CssBaseline from '@material-ui/core/CssBaseline';
import Grid from '@material-ui/core/Grid';
import Link from '@material-ui/core/Link';
import Typography from '@material-ui/core/Typography';


// NavBar sticks to the top of the page to handle logout and searching
const NavBar = props => {

    // grabs logged state variables from props
    const { logged, setLogged } = props;


    // function to handle logouts
    const handleLogout = () => {
        Axios.get('http://localhost:8000/api/logout', { withCredentials: true })
        .then(res => {
                setLogged(null);
                navigate('/');
            })
            .catch(err => console.log(err));
    };


    // returns NavBar
    return (
        <Navbar
            bg="dark"
            variant="dark"
            expand="lg"
        >
            <CssBaseline />
            <Grid
                container
                alignItems="center"
            >
                <Form
                    className="mr-auto"
                    inline
                >
                    <Navbar.Brand href="/">
                        <img
                            src={cd_icon}
                            width="35"
                            height="35"
                            className="d-inline-block mr-sm-1"
                            alt="CarpeDiction!"
                        />
                        <Typography className="d-inline-block">
                            <span className="rIGreen">
                                <strong>
                                    <i>
                                        CarpeDiction
                                    </i>
                                </strong>
                            </span>
                        </Typography>
                    </Navbar.Brand>
                    <FormControl
                        type="text"
                        placeholder="Search..."
                        className="mr-sm-2 searchBx"
                    />
                    <Button
                        className="sButt"
                        variant="warning"
                        type="submit"
                    >
                        <strong>
                            Search!
                        </strong>
                    </Button>
                </Form>
                <Nav>
                    <Navbar.Text>
                        {logged !== null &&
                            <>
                                Signed in as:&nbsp;
                                <Link
                                    href={"/user/account"}
                                    className="flatLink"
                                >
                                    <span className="rIOrange">
                                        <strong>
                                            {logged.userName}&nbsp;
                                        </strong>
                                    </span>
                                </Link>
                                Not you?&ensp;
                                <Button
                                    onClick={handleLogout}
                                    size="sm"
                                    variant="outline-danger"
                                    className="ml-sm-1"
                                >
                                    <span>
                                        <strong>
                                            Log Out
                                        </strong>
                                    </span>
                                </Button>
                            </>
                        }
                        {logged === null &&
                            <>
                                You are browsing as a guest!&nbsp;
                                <Link
                                    href={"/register"}
                                    className="flatLink"
                                >
                                    <span className="rIOrange">
                                        <strong>
                                            Register&nbsp;
                                        </strong>
                                    </span>
                                </Link>
                                or&ensp;
                                <Link
                                    href={"/login"}
                                    className="flatLink"
                                >
                                    <span className="rIOrange">
                                        <strong>
                                            Login&nbsp;
                                        </strong>
                                    </span>
                                </Link>
                                to access exclusive features!
                            </>
                        }
                    </Navbar.Text>
                </Nav>
            </Grid>
        </Navbar>
    );
};

export default NavBar;