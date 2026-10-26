# Product Management System – UI Mockups (Balsamiq)

Open in Balsamiq: **Project → Import → Mockups from BMML** (or File → Open in Balsamiq Wireframes desktop) and select the `.bmml` files.

| # | Screen | API used | Roles |
|---|--------|----------|-------|
| 01 | Sign in | `POST /api/auth/signin` | public |
| 02 | Register | `POST /api/auth/signup` | public |
| 03 | Product list + search/filter | `GET /api/products[?categoryId=]`, `GET /api/categories` | USER, MANAGER, ADMIN |
| 04 | Product list – no results / USER view | same | USER |
| 05 | Product detail | `GET /api/products/{id}` | all |
| 06 | Product create / edit form | `POST /api/products`, `PUT /api/products/{id}` | MANAGER, ADMIN |
| 07 | Delete confirmation | `DELETE /api/products/{id}` | ADMIN |
| 08 | Category list | `GET /api/categories`, `DELETE /api/categories/{id}` | read: all, delete: ADMIN |
| 09 | Category create / edit + its products | `POST/PUT /api/categories`, `GET /api/categories/{id}/products` | MANAGER, ADMIN |

## Notes
- The backend only filters products by `categoryId`. Keyword and price-range search in screen 03 must be done client-side on the returned list (or add `name`/`minPrice`/`maxPrice` query params to the API).
- All calls except sign-in/sign-up need `Authorization: Bearer <jwt>`; hide actions the signed-in role cannot perform.
