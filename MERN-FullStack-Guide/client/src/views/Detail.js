import React, { useEffect, useState } from 'react';

import Axios from '../../../server/node_modules/axios';
import { Link, navigate } from '@reach/router';

import NavBar from '../components/NavBar';
import StickyFooter from '../components/StickyFooter';

import Container from '@material-ui/core/Container';
import CssBaseline from '@material-ui/core/CssBaseline';
import { makeStyles } from '@material-ui/core/styles';



// CSS rulesets
const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
    },
    main: {
        marginTop: theme.spacing(8),
        marginBottom: theme.spacing(2),
    },
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
}));


// Detail view is for viewing account info
const Detail = props => {

    // retrieves the logged state variables and logout handler from props
    const { logged, setLogged } = props;

    // generates CSS rulesets
    const classes = useStyles();

    // local logged variable for the GET hook to track intermediately, preventing logout errors
    const [localUser] = useState(logged);

    // state variables to hold the User data and whether or not it has loaded
    const [user, setUser] = useState({});
    const [loaded, setLoaded] = useState(false);


    // redirects to home if no User logged in. Otherwise copies logged data to localUser
    useEffect(() => {
        if (logged === null)
            navigate('/login');
    });


    // retrieves the User data
    useEffect(() => {
        Axios.get("http://localhost:8000/api/users/" + localUser._id, { withCredentials: true })
            .then(res => {
                setUser(res.data.user);
                setLoaded(true);
            })
            .catch(err => {
                if (err.response.status === 401)
                    navigate('/login');
            });
    }, [localUser]);


    // returns the User account details screen
    return (
        <div className={classes.root}>
            <CssBaseline />
            <NavBar
                logged={logged}
                setLogged={setLogged}
            />
            <Container
                component="main"
                className={classes.main}
                maxWidth="sm"
            >
                <div className={classes.paper}>
                    {loaded && (
                        <>
                            <p>Username: {user.userName}</p>
                            <p>Email: {user.email}</p>
                        </>
                    )}
                    <br />
                    <Link
                        to={"/user/account/edit"}
                        className="flatLink"
                    >
                        <span className="rIPurple">
                            <strong>
                                Edit
                            </strong>
                        </span>
                    </Link>
                    <br />
                    <Link
                        to='/'
                        className="flatLink"
                    >
                        <span className="rIPurple">
                            <strong>
                                Go Back
                            </strong>
                        </span>
                    </Link>
                </div>
            </Container>
            <StickyFooter />
        </div>
    );
};

export default Detail;