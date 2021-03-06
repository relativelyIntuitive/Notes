import React, { useState } from 'react';

import cd_icon from '../static/images/cd_icon.png';

import { Button } from 'react-bootstrap';

import Container from '@material-ui/core/Container';
import CssBaseline from '@material-ui/core/CssBaseline';
import Grid from '@material-ui/core/Grid';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';



// CSS rulesets
const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));


// LoginForm receives User info and makes login request
const LoginForm = props => {

    // grabs the initialLog object, erros array and onSubmitProp function from props
    const { initialLog,
        onSubmitProp,
        errors } = props;

    // generates CSS rulesets
    const classes = useStyles();

    // state variables to keep track of what is being typed
    const [log, setLog] = useState(initialLog);


    // handler to update state on input change
    const handleInputChange = e => {
        setLog({
            ...log,
            [e.target.name]: e.target.value
        });
    };

    // handler when the form is submitted
    const onSubmitHandler = e => {
        // prevent default behavior of the submit
        e.preventDefault();
        // make a post request with the onSubmitProp to create a new User
        onSubmitProp(log);
    };


    // returns login form
    return (
        <Container
            component="main"
            maxWidth="xs"
        >
            <CssBaseline />
            <div className={classes.paper}>
                <img
                    src={cd_icon}
                    width="169"
                    height="169"
                    className="d-inline-block"
                    alt="CarpeDiction!"
                />
                <br />
                <Typography
                    component="h1"
                    variant="h5"
                >
                    <span className="rIPurple">
                        <strong>
                            Welcome Back!
                        </strong>
                    </span>
                </Typography>
                <br />
                <form
                    onSubmit={onSubmitHandler}
                    className={classes.form}
                    noValidate
                >
                    <Grid
                        container
                        justify="center"
                    >
                        <Grid item>
                            {errors.length > 0 && (
                                errors.map((err, index) => (
                                    <Typography
                                        className="text-danger"
                                        key={index}
                                    >
                                        <strong>
                                            {err}
                                        </strong>
                                    </Typography>
                                )
                            ))}
                        </Grid>
                    </Grid>
                    <TextField
                        className="rIFormControl"
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id="email"
                        label="Email:"
                        name="email"
                        autoComplete="email"
                        autoFocus
                        onChange={handleInputChange}
                    />
                    <TextField
                        className="rIFormControl"
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password:"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        onChange={handleInputChange}
                    />
                    <br />
                    <br />
                    <Button
                        type="submit"
                        variant="contained"
                        className="formButt"
                    >
                        <strong>
                            Sign in
                        </strong>
                    </Button>
                    <br />
                    <br />
                    <Grid
                        container
                        justify="flex-end"
                    >
                        <Link
                            href="/register"
                            className="flatLink"
                        >
                            <span className="rIPurple">
                                <strong>
                                    Don't have an account? Sign up here!*&emsp;
                                </strong>
                            </span>
                        </Link>
                    </Grid>
                    <hr />
                    <Grid
                        container
                        justify="flex-end"
                    >
                        <Link
                            href={"/"}
                            className="flatLink"
                        >
                            <span className="rIPurple">
                                <strong>
                                    Nevermind...&emsp;
                                </strong>
                            </span>
                        </Link>
                    </Grid>
                </form>
            </div>
        </Container>
    );
};

export default LoginForm;