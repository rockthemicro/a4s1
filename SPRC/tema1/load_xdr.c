/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "load.h"

bool_t
xdr_var (XDR *xdrs, var *objp)
{
	register int32_t *buf;

	int i;
	 if (!xdr_vector (xdrs, (char *)objp->resp, 4,
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	 if (!xdr_long (xdrs, &objp->p))
		 return FALSE;
	 if (!xdr_long (xdrs, &objp->q))
		 return FALSE;
	return TRUE;
}
