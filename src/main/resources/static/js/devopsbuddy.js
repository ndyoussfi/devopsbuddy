$( document ).ready( main );

function main() {

    $('.btn-collapse').click(function(e) {
        e.preventDefault();
        var $this = $(this);
        var $collapse = $this.closest('.collapse-group').find('.collapse');
        $collapse.collapse('toggle');
    });

    /* Contact form validation */
    $('#contactForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'The first name is required'
                    }
                }
            },
            lastName: {
                validators: {
                    notEmpty: {
                        message: 'The last name is required'
                    }
                }
            },
            feedback: {
                validators: {
                    notEmpty: {
                        message: 'Your feedback is valued and required'
                    }
                }
            }
        },
        plugins: {
            trigger: new FormValidation.plugins.Trigger(),
            bootstrap3: new FormValidation.plugins.Bootstrap3(),
            submitButton: new FormValidation.plugins.SubmitButton(),
            icon: new FormValidation.plugins.Icon({
                valid: 'fa fa-check',
                invalid: 'fa fa-times',
                validating: 'fa fa-refresh'
            })

        }
    });

    /* Forgot password form validation */
    $('#forgotPasswordForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            }
        },
        plugins: {
            trigger: new FormValidation.plugins.Trigger(),
            bootstrap3: new FormValidation.plugins.Bootstrap3(),
            submitButton: new FormValidation.plugins.SubmitButton(),
            icon: new FormValidation.plugins.Icon({
                valid: 'fa fa-check',
                invalid: 'fa fa-times',
                validating: 'fa fa-refresh'
            })

        }
    });

    $('#savePasswordForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: 'The password and its confirmation are not the same'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The confirmation password is required'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirmation are not the same'
                    }
                }
            }
        },
        plugins: {
            trigger: new FormValidation.plugins.Trigger(),
            bootstrap3: new FormValidation.plugins.Bootstrap3(),
            submitButton: new FormValidation.plugins.SubmitButton(),
            icon: new FormValidation.plugins.Icon({
                valid: 'fa fa-check',
                invalid: 'fa fa-times',
                validating: 'fa fa-refresh'
            })

        }
    });

    /* Login page form validation */
    $('#loginForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: 'The username is required'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    }
                }
            }
        },
        plugins: {
            trigger: new FormValidation.plugins.Trigger(),
            bootstrap3: new FormValidation.plugins.Bootstrap3(),
            submitButton: new FormValidation.plugins.SubmitButton(),
            icon: new FormValidation.plugins.Icon({
                valid: 'fa fa-check',
                invalid: 'fa fa-times',
                validating: 'fa fa-refresh'
            })

        }
    });

    /* Signup form validation */
    $('#signupForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: 'The username is required'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The confirmation password is required'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'The first name is required'
                    }
                }
            },
            lastName: {
                validators: {
                    notEmpty: {
                        message: 'The last name is required'
                    }
                }
            },
            description: {
                validators: {
                    stringLength: {
                        message: 'Post content must be less than 300 characters',
                        min: 0,
                        max: function (value, validator, $field) {
                            return 300 - (value.match(/\r/g) || []).length;
                        }
                    }
                }
            },
            phoneNumber: {
                validators: {
                    notEmpty: {
                        message: 'The phone number is required'
                    },
                    phone: {
                        country: 'country',
                        message: 'The value is not valid %s phone number'
                    }
                }
            },
            cardNumber: {
                validators: {
                    notEmpty: {
                        message: 'The credit card number is required'
                    },
                    creditCard: {
                        message: 'The credit card number is not valid'
                    }
                }
            },
            cardCode: {
                validators: {
                    notEmpty: {
                        message: 'The CVV number is required'
                    },
                    cardCode: {
                        creditCardField: 'cardNumber',
                        message: 'The CVV number is not valid'
                    }
                }
            }
        },
        plugins: {
            trigger: new FormValidation.plugins.Trigger(),
            bootstrap3: new FormValidation.plugins.Bootstrap3(),
            submitButton: new FormValidation.plugins.SubmitButton(),
            icon: new FormValidation.plugins.Icon({
                valid: 'fa fa-check',
                invalid: 'fa fa-times',
                validating: 'fa fa-refresh'
            })

        }
    })
    // Revalidate phone number when changing the country
        .on('change', '[name="country"]', function(e) {
            $('#signupForm').formValidation('revalidateField', 'phoneNumber');
        });

}